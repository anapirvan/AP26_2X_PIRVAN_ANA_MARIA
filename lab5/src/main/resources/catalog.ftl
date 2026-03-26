<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog Report</title>
</head>
<body>
<h1>Catalog Report</h1>
<p>Total items: <strong>${items?size}</strong></p>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Year</th>
        <th>Location</th>
    </tr>
    <#list items as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td>${item.author!""}</td>
            <td>${item.year!""}</td>
            <td>
                <#if item.location?starts_with("http")>
                    <a href="${item.location}" target="_blank">${item.location}</a>
                <#else>
                    ${item.location}
                </#if>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>