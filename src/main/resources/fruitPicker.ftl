<html>
<head>
  <title>Fruit picker</title>
</head>

<body>
  <form action="/favorite_fruit" method="POST">
    <h1>Choisissez votre fuit favorit :</h1>
    <#list fruits as fruit >
      <p>
        <input type="radio" name="fruit" value="${fruit}">${fruit}</input>
      </p>
    </#list>
    <input type="submit" value="Valider" />
  </form>
</body>
</html>