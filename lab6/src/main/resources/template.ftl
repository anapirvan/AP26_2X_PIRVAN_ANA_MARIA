<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Movie Report</title>
</head>
<body>
<h1>Movie Report</h1>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Release Date</th>
        <th>Duration (min)</th>
        <th>Score</th>
        <th>Genre</th>
    </tr>
    </thead>
    <tbody>
    <#list movies as movie>
        <tr>
            <td>${movie.id}</td>
            <td>${movie.title}</td>
            <td>${movie.releaseDate}</td>
            <td>${movie.duration}</td>
            <td class="score">${movie.score}</td>
            <td>${movie.genre}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
