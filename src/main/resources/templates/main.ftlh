<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout/>
</div>
<#-- "_csrf" needs only for POST-requests-->
<div>
    <form method="post" action="add">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input name="text" placeholder="Введите сообщение"/>
        <input name="tag" placeholder="Тэг"/>
        <button>Добавить</button>
    </form>
</div>
<div>Список сообщений</div>
<form method="get" action="/main">
    <input name="tag" placeholder="Фильтр" value="${RequestParameters.tag!}"/>
    <button>Найти</button>
</form>
    <#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
    <#else>
    No message
    </#list>
</@c.page>