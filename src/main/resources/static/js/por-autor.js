document.getElementById('buscarAutorForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const autorId = document.getElementById('autorId').value;
  const resultadoDiv = document.getElementById('resultado');

  try {
    const response = await fetch(`/livro/por-autor/${autorId}`);
    if (!response.ok) {
      throw new Error("Autor não encontrado ou não tem livros.");
    }

    const livros = await response.json();

    if (livros.length === 0) {
      resultadoDiv.innerHTML = `<p>Nenhum livro encontrado para esse autor.</p>`;
      return;
    }

    let html = "<h3>Livros:</h3><ul>";
    livros.forEach(livro => {
      html += `<li><strong>${livro.nome}</strong> (ID: ${livro.id})</li>`;
    });
    html += "</ul>";

    resultadoDiv.innerHTML = html;
  } catch (err) {
    resultadoDiv.innerHTML = `<p style="color:red;">${err.message}</p>`;
  }
});