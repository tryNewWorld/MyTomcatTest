function doDel(id) {
    if(confirm("是否确定删除")) {
        window.location.href = 'fruit.do?operate=del&fid='+id;
    }
}

function page(pageNo) {
    window.location.href = 'fruit.do?operate=index&pageNo='+pageNo;
}