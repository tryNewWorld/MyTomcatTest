function doDel(id) {
    if(confirm("是否确定删除")) {
        window.location.href = 'del.do?fid='+id;
    }
}

function page(pageNo) {
    window.location.href = 'index?pageNo='+pageNo;
}