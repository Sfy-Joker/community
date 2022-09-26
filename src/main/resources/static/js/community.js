$('#btn-reply').click(() => {
    let parentId = $('#input-question-id').val(),
        type = 1,
        content = $('.question-textarea').val();

    $.ajax({
        url: '/comment',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            'parentId': parentId,
            'type': type,
            'content': content
        }),
        success: res => {
            if (res.message == 'Comment content cannot be empty') {
                alert('回复内容为空,请输入。')
                $('.question-textarea').focus()
            } else if (res.code == 200) {
                alert('评论成功！')
            } else if (res.code == 2001) {
                var isAccepted = confirm(res.message)
                if(isAccepted){
                    
                }
                window.open('https://github.com/login/oauth/authorize?client_id=a2410b66c55b59755913&redirect_uri=http://localhost:8887/callback&state=1')
                window.localStorage.setItem('closable', 'true');
            } else {
                alert(res.message);
                $('.question-textarea').val('')
            }
        }
    });
});