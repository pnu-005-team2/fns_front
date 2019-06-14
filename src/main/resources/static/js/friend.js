function addfriend(mypid,fripid, createRow=true){
    let sendData = {
        "uid1" : mypid,
        "uid2" : fripid
    };

    $.ajax({
        type : "POST",
        url : "/friend/add",
        data : sendData,
        success: function (response) {
            console.log("Friends Add : "+ response);
            removeRow("data-recommend-index", response.youruid);
            if(createRow)
                createFriendItem(response);

            console.log("Complete");
        }
    });

}

function deletefriend(e){
    console.log(e.target);
    let target_uid = e.target.dataset["friendBtnIdx"];
    let source_uid = e.target.dataset['uid'];
    console.log("delete Click" + target_uid + ", " + source_uid);
    let sendData = {
        "uid1" : source_uid,
        "uid2" : target_uid
    };
    $.ajax({
        type : "POST",
        url : "/friend/delete",
        data : sendData,
        success: function (response) {
            removeRow("data-friend-index", response);
        }
    });
}



function initFriends() {
    console.log("Init Friends");
    let friend_list = document.querySelector(".follow");
    while (friend_list.hasChildNodes()) friend_list.lastChild.remove();
}

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
    delete_btn.classList.add("follow-btn");
    delete_btn.setAttribute("data-friend-btn-idx", freind_infor.uid);
    delete_btn.onclick = deletefriend;

    row.appendChild(img);
    row.appendChild(name);
    row.appendChild(delete_btn);

    follow_box.appendChild(row);
}

//----------- target_name 속성이 idx인 Row를 제거합니다. ---------------
function removeRow(target_name, idx) {
    console.log(target_name);
    console.log(idx);
    let target_row = document.querySelector(`div[${target_name}="${idx}"]`);
    console.log(target_row);
    target_row.remove();
}


