console.log("Script")

document.getElementById('buscarForm').addEventListener('submit', async function () {


  const id = document.getElementById('id').value;
  const resultadoDiv = document.getElementById('resultado');

  try {
    const response = await fetch(`/usuarios/ver/${id}`);
    if (!response.ok) {
      throw new Error("Usuário não encontrado");
    }

    const usuario = await response.json();

    resultadoDiv.innerHTML = `
      <h3>Informações do Usuário:</h3>
      <p><strong>ID:</strong> ${usuario.id}</p>
      <p><strong>Nome:</strong> ${usuario.nome}</p>
      <p><strong>Idade:</strong> ${usuario.idade}</p>
      <p><strong>Endereco:</strong> ${usuario.endereco}</p>
    `;
  } catch (err) {
    resultadoDiv.innerHTML = `<p style="color:red;">${err.message}</p>`;
  }
});