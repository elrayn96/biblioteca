console.log("Script")

document.getElementById('buscarForm').addEventListener('submit', async function () {


  const id = document.getElementById('id').value;
  const resultadoDiv = document.getElementById('resultado');

  try {
    const response = await fetch(`/autor/ver/${id}`);
    if (!response.ok) {
      throw new Error("Autor não encontrado");
    }

    const autor = await response.json();

    resultadoDiv.innerHTML = `
      <h3>Informações do Autor:</h3>
      <p><strong>ID:</strong> ${autor.id}</p>
      <p><strong>Nome:</strong> ${autor.nome}</p>
    `;
  } catch (err) {
    resultadoDiv.innerHTML = `<p style="color:red;">${err.message}</p>`;
  }
});