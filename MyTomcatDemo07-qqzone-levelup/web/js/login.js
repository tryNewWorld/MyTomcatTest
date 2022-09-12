function login() {
    var account = document.getElementById("account").value;
    var pwd = document.getElementById("pwd").value;
    window.location.href="user.do?operate=login&account="+account+"&pwd="+pwd;
}