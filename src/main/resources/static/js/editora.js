console.log("Script")

document.getElementById('buscarForm').addEventListener('submit', async function () {


  const id = document.getElementById('id').value;
  const resultadoDiv = document.getElementById('resultado');

  try {
    const response = await fetch(`/editora/ver/${id}`);
    if (!response.ok) {
      throw new Error("Editora não encontrada");
    }

    const editora = await response.json();

    resultadoDiv.innerHTML = `
      <h3>Informações da Editora:</h3>
      <p><strong>ID:</strong> ${editora.id}</p>
      <p><strong>Nome:</strong> ${editora.nome}</p>
      <p><strong>Telefone:</strong> ${editora.telefone}</p>
      <p><strong>Endereco:</strong> ${editora.endereco}</p>
      
    `;
  } catch (err) {
    resultadoDiv.innerHTML = `<p style="color:red;">${err.message}</p>`;
  }
});