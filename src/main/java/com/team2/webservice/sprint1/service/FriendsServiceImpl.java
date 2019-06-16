package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.FriendsRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Friends;
import com.team2.webservice.sprint1.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class FriendsServiceImpl implements FriendsService{

    private static final Logger logger =LoggerFactory.getLogger(FriendsServiceImpl.class);

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    FriendsRepository friendsRepository;

    @Override
    public Member addFriends(int uid1, int uid2) {
        //mem1 :(본인) mem2를 본인의 친구목록에 추가 하고자 하는사람
        //mem2 : 추가 당하는 사람
        Optional<Member>  rst = null;
        boolean isExistence = friendsRepository.existsByMyuidAndYouruid(uid1, uid2);

        if(isExistence){
            logger.error("이미 존재하는 친구정보입니다.");

        } else{
            Friends newfri = new Friends();
            newfri.setMyuid(uid1);
            newfri.setYouruid(uid2);
            newfri.setYourname(setMember(uid2).getName());
            friendsRepository.save(newfri);
            rst = memberRepository.findById(uid2);
            logger.error("친구 추가를 완료했습니다.");
        }
        if(!rst.isPresent()) return null;

        return rst.get();
    }

    @Override
    public void deleteFriend(int uid1, int uid2){
        //mem1 :(본인)
        //mem2 : 삭제 당하는 사람

        Optional<Friends> friends = friendsRepository.findByMyuidAndYouruid(uid1, uid2);
        if(!friends.isPresent()){
            logger.error("삭제할 친구정보가 존재하지 않습니다.");
            return;
        } else{
            friendsRepository.delete(friends.get());
            logger.info("친구 삭제 완료");
        }

        return;

    }

    @Override
    public List<Member> showFollowing(int me) {
        logger.info("Entry showFollowing");
        List<Object> friends = friendsRepository.getYouruidByMyuid(me);
        List<Member> members = memberRepository.findByUidIn(friends);
        return members;
    }

    @Override
    public List<Member> showFollower(int me){
        logger.info("Entry showFollower");
        List<Object> friends = friendsRepository.getMyuidByYouruid(me);
        List<Member> members = memberRepository.findByUidIn(friends);
        return  members;
    }

    @Override
    public List<Board> showFBoard(Member me){
        logger.info("Entry showFBoard");
        List<Board> myBoards = boardRepository.findByMemberUid(me.getUid());

        List<Object> myFriends = friendsRepository.getYouruidByMyuid(me.getUid());
        if(myFriends.size() == 0){
            logger.info("게시물이 없습니다.");
            return null;
        }
        List<Board> friendBoards = boardRepository.findByMemberUidIn(myFriends);

        myBoards.addAll(friendBoards);
        Collections.sort(myBoards);

        return myBoards;
    }

    @Override
    public List<Member> recommendFriends(Member me){
        List<Member>  all = memberRepository.findAll();
        ArrayList<Member> recoI = new ArrayList<>();
        List<Member> myfri = showFollowing(me.getUid());
        boolean check = true;

        for(int i = 1; i < all.size(); ++i){
            check = true;
            if(all.get(i).getUid() == me.getUid()){
                check = false;
            }
            if(myfri.size() != 0) {
                for (int j = 0; j < myfri.size(); ++j) {
                    if (all.get(i).getUid() == myfri.get(j).getUid()) {
                        check = false;
                        break;
                    }
                }
            }
            if(check){
                recoI.add(all.get(i));
            }
        }
        long seed = System.nanoTime();
        Collections.shuffle(recoI, new Random(seed));

        return recoI;
    }


    @Override
    public Member findAFriend(Member me, String findName) {

        return null;
    }

    public List<Member> loadFriendsByKeyword(int uid, String keyword){
        List<Member> rst = null;
        if(keyword == null) rst = showFollowing(uid);
        else if(keyword.equals("following")) rst = showFollowing(uid);
        else if(keyword.equals("follower")) rst = showFollower(uid);

        return rst;
    }

    public boolean isFollowing(int uid, String candi){
        return friendsRepository.existsByMyuidAndYourname(uid,candi);
    }

    //중복코드라서,
    public Member setFriendListToModel(Model model, HttpServletRequest request){
        //about Friends controlling, http session
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("login"));
        Member me = (Member)session.getAttribute("login");

        //show following
        List<Member> Friends = this.showFollowing(me.getUid());

        model.addAttribute("friendsRecordList", Friends);

        //show follower
        List<Member> Friended = this.showFollower(me.getUid());

        model.addAttribute("friendedRecordList", Friended);

        //show recomend
        List<Member> recommend = this.recommendFriends(me);
        model.addAttribute("friendRecommendList", recommend);

        return updateLoginSession(me.getEmail(), session);
    }


    // ----------- Sesssion에 들어있는 Login 객체를 업데이트합니다. -------------
    private Member updateLoginSession(String email, HttpSession session) {
        Optional<Member> update_me = memberRepository.findByEmail(email);

        if(!update_me.isPresent()){
            System.out.println("세션갱신에 오류가 발생했습니다.");
            return null;
        }

        session.setAttribute("login", update_me.get());
        return update_me.get();
    }

    protected Member setMember(int uid){

        List<Member> all = memberRepository.findAll();
        Member ret = null;
        for(int i = 0 ; i < all.size() ; ++i){
            if(all.get(i).getUid() == uid){
                ret = all.get(i);
            }
        }
        return ret;
    }

}

