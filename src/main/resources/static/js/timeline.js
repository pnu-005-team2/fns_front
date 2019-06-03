

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

function removeRow(target_name, idx) {
    // let target_row = document.querySelector("div[data-friend-index='"+response+"']");
    let target_row = document.querySelector(`div[${target_name}="${idx}"]`);
    console.log(target_row);
    target_row.remove();
}
