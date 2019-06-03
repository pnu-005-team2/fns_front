package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Board;
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

    @Override
    public Member addFriends(int uid1, int uid2) {
        //mem1 :(본인) mem2를 본인의 친구목록에 추가 하고자 하는사람
        //mem2 : 추가 당하는 사람
        System.out.println("Service add Friends");
        Member mem1 = setMember(uid1);
        Member mem2 = setMember(uid2);
        boolean check = true;


        List<Member> fList1 = showFList(mem1, mem1.getFriends());
        for(int i = 0 ; i < fList1.size() ; ++i){
            if(fList1.get(i) == mem2){
                check = false;
            }
        }

        if(check){
            String temp1;
            String temp2;
            String newFriends ;

            temp1 = mem1.getFriends();
            temp2 = mem2.getUid() + " ";
            newFriends = temp1 + temp2;
            mem1.setFriends(newFriends);
            memberRepository.save(mem1);


            temp1 = mem2.getFriended();
            String newFriended ;
            temp2 = mem1.getUid() + " ";
            newFriended = temp1 + temp2;

            mem2.setFriended(newFriended);
            mem2 = memberRepository.save(mem2);
        }
        else{
            System.out.println("already friends");
        }

        return mem2;
    }

    @Override
    public void deleteFriend(int uid1, int uid2){
        //mem1 :(본인)
        //mem2 : 삭제 당하는 사람

        Member mem1 = setMember(uid1);
        Member mem2 = setMember(uid2);
        String delList = "";

        List<Member> friList = showFList(mem1, mem1.getFriends());

        for(int i = 0; i < friList.size() ; ++i){
            if(uid2 != friList.get(i).getUid()){
                delList += friList.get(i).getUid() + " ";
            }
        }

        //Todo 언팔로워 계정에서 팔로잉 삭제작업 필요

        mem1.setFriends(delList);
        memberRepository.save(mem1);

    }

    @Override
    public List<Member> showFriends(Member me) {

        String friends = me.getFriends();

        return showFList(me, friends);
    }

    @Override
    public List<Member> showFriended(Member me){

        String friended = me.getFriended();

        return  showFList(me, friended);
    }

    @Override
    public List<Board> showFBoard(Member me){
        return showFriBoard(me);
    }

    @Override
    public List<Member> recommendFriends(Member me){
        List<Member>  all = memberRepository.findAll();
        ArrayList<Member> recoI = new ArrayList<>();
//        List<Member> reco  = new List<Member>();
        List<Member> myfri = showFList(me, me.getFriends());
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
//                System.out.println("uid check     " + i);
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


    protected List<Board> showFriBoard(Member me){
        ArrayList<Board> fbList = new ArrayList<>();

        List<Board> all =  boardRepository.findAll();
        List<Member> myfri = showFList(me, me.getFriends());

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

    protected List<Member> showFList(Member me, String fList) {

        ArrayList<Integer> fIList = new ArrayList<>();

        String temp = "";
        for(int i = 0 ; i < fList.length() ; ++i){

            if(fList.charAt(i) >= 48 && fList.charAt(i) <= 57) {
                char cTemp = fList.charAt(i);
                temp += cTemp;
            }else if(temp != "" || temp != " "){
                int iTemp = Integer.parseInt(temp);
                fIList.add(iTemp);
                temp = "";
            }

        }

        List<Member> member = memberRepository.findAll();
        ArrayList<Member> fListR = new ArrayList<>();

        for(int i = 0 ; i < member.size(); ++i){
            for(int j = 0 ; j < fIList.size() ; ++j){
                if(member.get(i).getUid() == (long) fIList.get(j) ){
                    fListR.add(member.get(i));
                    break;
                }
            }
        }

        return fListR;
    }

    //중복코드라서,
    public Member setFriendListToModel(Model model, HttpServletRequest request){
        //about Friends controlling, http session
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("login"));
        Member me = (Member)session.getAttribute("login");

        //show following
        List<Member> Friends = this.showFriends(me);

        model.addAttribute("friendsRecordList", Friends);
        model.addAttribute("friendsRecordList_Byte", Friends);

        //show follower
        List<Member> Friended = this.showFriended(me);

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

