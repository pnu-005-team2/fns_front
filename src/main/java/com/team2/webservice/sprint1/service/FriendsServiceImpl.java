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

        System.out.println("show Friends List");
        System.out.println(me.getName());
        String friends = me.getFriends();

        System.out.println(me.getFriends());
        System.out.println(friends);

        return showFList(me, friends);
    }

    @Override
    public List<Member> showFriended(Member me){

        System.out.println("show Friended List");
        String friended = me.getFriended();

        return  showFList(me, friended);
    }


    @Override
    public Member findAFriend(Member me, String findName) {

        return null;
    }



    public List<Member> showFList(Member me, String fList) {

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
}

