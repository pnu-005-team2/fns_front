

//----------- Folloing Row를 만듭니다. ---------------
function createFriendItem(freind_infor) {
    let follow_box = document.querySelector(".following");
    let row = document.createElement("div");
    let img = document.createElement("img");
    let name = document.createElement("strong");
    let delete_btn = document.createElement("button");

    row.setAttribute("data-friend-index", freind_infor.uid);
    row.classList.add("friend-item");
    name.textContent = freind_infor.name;
    img.src = freind_infor.img;
    img.classList.add("profile");
    delete_btn.textContent = "Unfollow";
    delete_btn.setAttribute("data-friend-btn-idx", freind_infor.uid);
    delete_btn.onclick = deletefriend;

    row.appendChild(img);
    row.appendChild(name);
    row.appendChild(delete_btn);

    follow_box.appendChild(row);
}

//----------- target_name 속성이 idx인 Row를 제거합니다. ---------------
function removeRow(target_name, idx) {
    let target_row = document.querySelector(`div[${target_name}="${idx}"]`);
    console.log(target_row);
    target_row.remove();
}


//----------- 덧글 Row를 만듭니다.---------------
function createComment(comment_info, user_name) {
    let comment_row = document.createElement("div");
    let target_comment_list = document.querySelector(`div[data-board-idx="${comment_info.pid}"]`);
    let comment_content = document.createElement("p");
    let comment_writer = document.createElement("a");
    let comment_delete= document.createElement("button");

    console.log(target_comment_list);

    comment_row.classList.add("comment-item");
    comment_row.setAttribute("data-comment-idx", comment_info.cid);

    comment_writer.textContent = user_name;
    comment_writer.href = `user/mypage?email=${comment_info.member.email}`;
    comment_writer.classList.add("writer");

    comment_delete.classList.add("delete-btn");
    comment_delete.textContent = "&times";
    comment_delete.onclick = commentDelete;

    comment_content.textContent = comment_info.content;

    comment_row.appendChild(comment_writer);
    comment_row.appendChild(comment_content);
    target_comment_list.append(comment_row);

}

//----------- 덧글을 삭제합니다..---------------
function commentDelete(e) {
    console.log("commentDelete");
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
