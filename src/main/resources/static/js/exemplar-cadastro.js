document.addEventListener("DOMContentLoaded", () => {

  fetch("/livro/lista/json")
    .then(res => res.json())
    .then(livros => {
      const select = document.getElementById("livroId");
      livros.forEach(livro => {
        const option = document.createElement("option");
        option.value = livro.id;
        option.textContent = livro.nome;
        select.appendChild(option);
      });
    });
});

