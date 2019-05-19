 package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Service
public class FriendsServiceImpl implements FriendsService{
    @Autowired
    MemberRepository memberRepository;

    @Override
    public void addFriends(Member mem1, Member mem2) {
        //mem1 : mem2를 본인의 친구목록에 추가 하고자 하는사람
        //mem2 : 추가 당하는 사람
        String temp1 = mem1.getFriends();
        String temp2 = " " + mem2.getUid();
        String newFriends = temp1 + temp2;
        mem1.setFriends(newFriends);

        temp1 = mem2.getFriended();
        temp2 = " " + mem1.getUid();
        String newFriended = temp1 + temp2;
        mem2.setFriended(newFriended);
    }

    @Override
    public List<Member> showFriends(Member me) {
        String friends = me.getFriends();
        ArrayList<Integer> friendList = new ArrayList<>();

        for(int i = 0 ; i < friends.length() ; ++i){

            String temp = "";
            while(friends.charAt(i) != ' ' || i < friends.length()){
                char cTemp = friends.charAt(i++);
                temp += cTemp;
            }
            int iTemp = parseInt(temp);
        }


        List<Member> member = memberRepository.findAll();
        ArrayList<Member> friendsListR = new ArrayList<>();

        for(int i = 0 ; i < member.size(); ++i){
            for(int j = 0 ; j < friendList.size() ; ++j){
                if(member.get(i).getUid() == (long) friendList.get(j) ){
                    friendList.remove(j);
                    friendsListR.add(member.get(i));
                    break;
                }
            }
        }

        return friendsListR;
    }

    @Override
    public List<Member> showFriended(Member me){
        String friended = me.getFriended();
        ArrayList<Integer> friendedList = new ArrayList<>();

        for(int i = 0 ; i < friended.length() ; ++i){
            String temp = "";
            while(friended.charAt(i) != ' ' || i < friended.length()){
                char cTemp = friended.charAt(i++);
                temp += cTemp;
            }
            int iTemp = parseInt(temp);
        }

        List<Member> member = memberRepository.findAll();
        ArrayList<Member> friendedListR = new ArrayList<>();

        for(int i = 0 ; i < member.size(); ++i){
            for(int j = 0 ; j < friendedList.size() ; ++j){
                if(member.get(i).getUid() == (long) friendedList.get(j) ){
                    friendedList.remove(j);
                    friendedListR.add(member.get(i));
                    break;
                }
            }
        }

        return friendedListR;

    }


    @Override
    public Member findAFriend(Member me, String findName) {



        return null;
    }
}
