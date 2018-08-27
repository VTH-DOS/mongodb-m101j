<html>
<head>
  <title>Favorite fruit</title>
</head>

<body>
    <h1>Résultat</h1>
    <#if !hasResult >
      <p>Merci de choisir un fruit dans la liste</p>
    </#if>
    <#if hasResult >
      <p>Votre fruit préféré est : ${favoriteFruit}</p>
    </#if>

    <p>
      <a href="${backLink}">Retour</a>
    </p>
    <p>
      Jouer avec les <a href="${objectsLink}">objets</a>
    </p>
</body>
</html>