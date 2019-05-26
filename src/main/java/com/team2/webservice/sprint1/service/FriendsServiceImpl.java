package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Service
public class FriendsServiceImpl implements FriendsService{
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;

    @Override
    public void addFriends(int uid1, int uid2) {
        //mem1 :(본인) mem2를 본인의 친구목록에 추가 하고자 하는사람
        //mem2 : 추가 당하는 사람

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
            String temp1 = mem1.getFriends();
            String temp2;
            String newFriends ;
            if(temp1 == null){
                temp2 = "" + mem2.getUid();
                newFriends = temp2;
            }
            else{
                temp2 = " " + mem2.getUid();
                newFriends = temp1 + temp2;
            }

            mem1.setFriends(newFriends);
            memberRepository.save(mem1);


            temp1 = mem2.getFriended();
            String newFriended ;
            if(temp1 == null){
                temp2 = "" + mem1.getUid();
                newFriended = temp2;
            }
            else{
                temp2 = " " + mem1.getUid();
                newFriended = temp1 + temp2;
            }

            mem2.setFriended(newFriended);
            memberRepository.save(mem1);
        }
        else{
            System.out.println("already friends");
        }
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

            if(fList.charAt(i) == ' ' ||
                    (i == fList.length()-1 && fList.charAt(i) < 30 && fList.charAt(i) > 39)) {
                System.out.println("index : " + i);
                int iTemp = Integer.parseInt(temp);
                fIList.add(iTemp);
                temp = "";
                continue;
            }
            char cTemp = fList.charAt(i);
            temp += cTemp;
            System.out.println("test----------------" + temp);

            if(i == fList.length()-1){
                int iTemp = Integer.parseInt(temp);
                fIList.add(iTemp);
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

