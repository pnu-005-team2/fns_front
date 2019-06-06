// --------- 댓글등록 ---------
function comment_regist(e){
    console.log("체크");
    let input_box = e.target.previousElementSibling;
    let pid = input_box.getAttribute("data-board-idx");
    let writer = input_box.getAttribute("data-uid");
    let writer_name = e.target.getAttribute("data-writer");
    let content = input_box.value;

    let sendData = {
        "pid" : pid,
        "uid" : writer,
        "content": content
    };

    $.ajax({
        type : "POST",
        url : "/comment",
        datatype : "json",
        data : sendData,
        success: function (response) {
            console.log(response);
            createComment(response, true);
        }
    });

    input_box.value='';

}

// --------- 댓글을 불러옵니다. ---------
function loadComment(e) {
    let pid = e.target.getAttribute("data-board-idx");
    let page = e.target.getAttribute("page-idx");
    let size = 4;
    console.log(pid);
    $.ajax({
        type : "POST",
        url : `/comment/load?size=${size}&page=${page}&sort=cid,desc`,
        data : {pid: pid},
        success: function (data) {
            console.log(data);
            console.log(pid);
            if(data.length === 0){
                alert("댓글이 없습니다.");
                return;
            }
            data.forEach((item)=>{
                createComment(item)
            });
            MoreShowBtnHandler(pid, page);
        }
    });

}

//----------- 덧글 Row를 만듭니다.---------------
function createComment(comment_info, addFront=false) {
    let comment_row = document.createElement("div");
    let target_comment_list = document.querySelector(`div[data-board-idx="${comment_info.pid}"]`);
    let comment_content = document.createElement("p");
    let comment_writer = document.createElement("a");
    let comment_delete= document.createElement("span");
    let comment_date = document.createElement("span");

    let time = new Date().getTime() -new Date(comment_info.created_date).getTime() ;

    let second = Math.floor(time/1000);
    let minute = Math.floor(second/60);
    let hour = Math.floor(minute/60);
    let day = Math.floor(hour/24);
    let month = Math.floor(day/30);
    let year = Math.floor(month/12);

    console.log(target_comment_list);

    comment_row.classList.add("comment-item");
    comment_row.setAttribute("data-comment-idx", comment_info.cid);

    comment_writer.textContent = comment_info.member.name;
    comment_writer.href = `user/mypage?email=${comment_info.member.email}`;
    comment_writer.classList.add("writer");

    comment_delete.classList.add("delete-btn");
    comment_delete.textContent = "\u02DF";
    comment_delete.style.fontSize = "1.8em";
    comment_delete.onclick = deleteComment;

    if( second <60)
        comment_date.textContent = second + "초";
    else if(minute <60)
        comment_date.textContent = minute + "분";
    else if(hour <24)
        comment_date.textContent = hour + "시간";
    else if(day <30)
        comment_date.textCOntent = day + "일";
    else if(month < 12)
        comment_date.textContent = month + "달";
    else
        comment_date.textContent = year + "년";
    comment_date.style.float="right";

    comment_content.textContent = comment_info.content;

    comment_row.appendChild(comment_writer);
    comment_row.appendChild(comment_delete);
    comment_row.appendChild(comment_date);
    comment_row.appendChild(comment_content);

    if(addFront)
        target_comment_list.insertBefore(comment_row, target_comment_list.firstChild);
    else
        target_comment_list.append(comment_row);

}

//----------- 덧글을 삭제합니다..---------------
function deleteComment(e) {
    console.log("deleteComment");
    let target = e.target.parentElement;
    let target_cid = target.getAttribute("data-comment-idx");

    $.ajax({
        type : "POST",
        url : "/comment/delete",
        datatype : "json",
        data : {cid : target_cid},
        success: function (response) {
            console.log(response);
            if(response)
                target.remove();
            else
                alert("삭제에 실패하였습니다");
        }
    })
}

//----------- 덧글을 댓글을 초기화합니다.---------------
function initComment(pid) {
    let comment_list = document.querySelector(`div[data-board-idx="${pid}"]`);
    // while(comment_list.hasChildNodes()) comment_list.removeChild(comment_list.lastChild);
    while(comment_list.hasChildNodes()) comment_list.lastChild.remove();
}


//----------- 더보기 버튼을 관리합니다.---------------
function MoreShowBtnHandler(pid, idx=0) {
    let target_comment_box = document.querySelector(`div[name="comment/${pid}"]`);
    console.log("create More");
    console.log(target_comment_box);

    // 더보기 버튼이 없으면 더보기 버튼 생성, 있다면 index만 갱신
    if(target_comment_box.childElementCount < 3){
        let more_show_btn = createMoreShowBtn(pid, idx);
        target_comment_box.appendChild(more_show_btn);
    } else {
        console.log("Add Idex");
        let more_show_btn = target_comment_box.lastChild;
        more_show_btn.setAttribute("page-idx", idx+1);
    }


}

//----------- 더보기 버튼을 만듭니다.---------------
function createMoreShowBtn(pid, idx) {
    console.log("Create More BTN");
    let more_show_btn = document.createElement("button");
    more_show_btn.onclick = loadComment;
    more_show_btn.setAttribute("data-board-idx", pid);
    more_show_btn.setAttribute("page-idx", idx+1);
    more_show_btn.classList.add("more-show-btn");
    more_show_btn.textContent = "더보기...";
    return more_show_btn;
}

