package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.FriendsRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Friends;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Service
public class FriendsServiceImpl implements FriendsService{

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    FriendsRepository friendsRepository;

    @Override
    public boolean addFriends(int uid1, int uid2) {
        //mem1 :(본인) mem2를 본인의 친구목록에 추가 하고자 하는사람
        //mem2 : 추가 당하는 사람
        boolean canadd = true;


        List<Friends> allfri = friendsRepository.findAll();

        for(int i = 0 ; i < allfri.size() ; ++i){
            if(allfri.get(i).getMyuid() == uid1 && allfri.get(i).getYouruid() == uid2){
                canadd = false;
                System.out.println("친구 추가 할수 없음");
                break;
            }
        }

        if(canadd){
            Friends newfri = new Friends();
            newfri.setMyuid(uid1);
            newfri.setYouruid(uid2);
            newfri.setYourname(setMember(uid2).getName());

            System.out.println("friends add test : " + newfri.getYourname());

            friendsRepository.save(newfri);
        }



        return canadd;
    }

    @Override
    public void deleteFriend(int uid1, int uid2){
        //mem1 :(본인)
        //mem2 : 삭제 당하는 사람

        List<Friends> allfri = friendsRepository.findAll();

        for(int i = 0 ; i < allfri.size() ; ++i){
            if(allfri.get(i).getMyuid() == uid1 && allfri.get(i).getYouruid() == uid2){
                friendsRepository.delete(allfri.get(i));
            }
        }


    }

    @Override
    public List<Member> showFollowing(int me) {

        return showFList(me, 0);
    }

    @Override
    public List<Member> showFollower(int me){

        return  showFList(me, 1);
    }

    @Override
    public List<Board> showFBoard(Member me){
        return showFriBoard(me);
    }

    @Override
    public List<Member> recommendFriends(Member me){
        List<Member>  all = memberRepository.findAll();
        ArrayList<Member> recoI = new ArrayList<>();
        List<Member> myfri = showFList(me.getUid(), 0);
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


    protected List<Board> showFriBoard(Member me){
        ArrayList<Board> fbList = new ArrayList<>();

        List<Board> all =  boardRepository.findAll();
        List<Member> myfri = showFList(me.getUid(), 0);

        boolean check ;

        for(int i = all.size() -1 ; i >= 0 ; --i){
            check = false;
            for(int j = 0 ; j < myfri.size() ; ++j){
                if(all.get(i).getMember().getUid() == me.getUid() ||
                    all.get(i).getMember().getUid() == myfri.get(j).getUid()){
                    check= true;
                }
            }
            if(check)
                fbList.add(all.get(i));
        }
        return fbList;
    }

    protected List<Member> showFList(int me, int ftype) {

        //ftype : 0 = following , 1 = follower
        List<Friends> allfriends = friendsRepository.findAll();
        ArrayList<Member> myfollowing  = new ArrayList<>();
        ArrayList<Member> myfollwer = new ArrayList<>();

        for(int i = 0 ; i < allfriends.size() ; i++){
            if(allfriends.get(i).getMyuid() == me){
                Member following = setMember(allfriends.get(i).getYouruid());
                myfollowing.add(following);
            }
            else if(allfriends.get(i).getYouruid() == me){
                Member follower = setMember(allfriends.get(i).getMyuid());
                myfollwer.add(follower);
            }
        }

        if(ftype == 0){
            return myfollowing;
        }
        else{
            return  myfollwer;
        }
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
        model.addAttribute("friendsRecordList_Byte", Friends);

        //show follower
        List<Member> Friended = this.showFollower(me.getUid());

        model.addAttribute("friendedRecordList", Friended);
        model.addAttribute("friendedRecordList_Byte", Friended);

        //show recomend
        List<Member> recommend = this.recommendFriends(me);

        model.addAttribute("friendRecommendList", recommend);
        model.addAttribute("friendRecommendList_Byte", recommend);

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

