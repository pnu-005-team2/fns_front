

// ---------- 해시태그 ,를 #으로 바꿔줍니다. ----------
function tagInit() {
    let hashtags = document.getElementsByClassName("board-hashtag");
    for(let item of hashtags) item.textContent = item.textContent.replace(/,/g, "#");
}
