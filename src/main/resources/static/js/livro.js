console.log("Script");

document.getElementById('buscarForm').addEventListener('submit', async function () {
  
  
  const id = document.getElementById('id').value;
  const resultadoDiv = document.getElementById('resultado');

  try {
    const response = await fetch(`/livro/ver/${id}`);
    if (!response.ok) {
      throw new Error("Livro não encontrado");
    }

    const livro = await response.json();

    // Gera a lista de autores em HTML
    const autoresHTML = livro.autor
      .map(a => `<li>${a.nome}</li>`)
      .join('');

    resultadoDiv.innerHTML = `
      <h3>Informações do Livro:</h3>
      <p><strong>ID:</strong> ${livro.id}</p>
      <p><strong>Nome:</strong> ${livro.nome}</p>
      <p><strong>Editora:</strong> ${livro.editora.nome}</p>
      <p><strong>Área de Conhecimento:</strong> ${livro.area.nome}</p>
      <p><strong>Autores:</strong></p>
      <ul>${autoresHTML}</ul>
    `;
  } catch (err) {
    resultadoDiv.innerHTML = `<p style="color:red;">${err.message}</p>`;
  }
});