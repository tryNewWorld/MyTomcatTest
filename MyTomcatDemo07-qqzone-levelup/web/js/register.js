function register() {
    var account = document.getElementById("account").value;
    var name = document.getElementById("name").value;
    var pwd = document.getElementById("pwd").value;
    window.location.href="user.do?operate=register&account="+account+"&name="+name+"&pwd="+pwd;
}