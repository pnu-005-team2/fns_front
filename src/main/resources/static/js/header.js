
$(document).ready(function (e) {
    let search_bar = document.getElementById("search-user-text");

    search_bar.onkeydown = text_Check;
});

function text_Check(e){
    console.log("Text Check" + e.key);
    let text_value = e.target.value+ e.key;
    let sendData__ = { "keyword" : text_value};
    initSearchBox();
    if(text_value === '') return;

    $.ajax({
        type : "POST",
        url : "/search/load",
        data : sendData__,
        success: function (response) {

            console.log(response);
            if(response){
                let search_result_box= document.getElementById("search-result-box");
                response.forEach(item => {
                    let search_item = document.createElement("div");
                    let search_profile = document.createElement("img");
                    let search_name= document.createElement("span");

                    search_item.classList.add("search-item");
                    search_item.onclick = ()=> {
                        location.href = "/user/mypage?email=" + item.email;
                    };

                    search_profile.classList.add("search-profile");
                    search_name.classList.add("search-name");
                    search_profile.src = item.img;
                    search_name.textContent = item.name;

                    search_item.appendChild(search_profile);
                    search_item.appendChild(search_name);
                    search_result_box.appendChild(search_item);
                });

                console.log(search_result_box);

            }
        }
    });

    console.log(sendData__ + "sendData__ : ");


    if(text_value.substr(0,1)==="#"){
        let size =5;
        let page =0;

        initSearchBox();
        $.ajax({
            type : "POST",
            url : "/search/hash?size="+size+"&page="+page,
            data : sendData__,
            success: function (response) {

                console.log("response :: "+response);
                if(response){
                    let search_result_box= document.getElementById("search-result-box");
                    //#.시도


                    response.forEach(item => {
                        let search_item = document.createElement("div");
                        let search_profile = document.createElement("img");
                        let search_name= document.createElement("span");
                        search_item.classList.add("search-item");
                        search_item.onclick = ()=> {

                            location.href = "/user/mypage?email=" + item.email+ "&pid="+item.pid;
                        };
                        search_profile.classList.add("search-profile");
                        search_name.classList.add("search-name");
                        console.log("response : "+ item.pid);
                        search_profile.src = "/logoShowForStudent/"+item.pid;
                        search_name.textContent = item.member.name;

                        search_item.appendChild(search_profile);
                        search_item.appendChild(search_name);
                        search_result_box.appendChild(search_item);
                    });
                    console.log(search_result_box);
                }
            }
        });
    }
}
function initSearchBox() {

    let temp=   document.getElementById("search-result-box");
    while(temp.hasChildNodes()){
        temp.removeChild(temp.lastChild);
    }

}