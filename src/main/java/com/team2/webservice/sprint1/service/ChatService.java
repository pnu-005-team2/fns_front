package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.vo.Member;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.List;

public interface ChatService {

    List<Member> loadChatList(Member uid);
}
