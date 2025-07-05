
document.getElementById("danificarForm").addEventListener("submit", async function (event) {
  event.preventDefault();

  const id = document.getElementById("id").value;
  const resultadoDiv = document.getElementById("resultado");

  try {
    const response = await fetch(`/exemplar/danificar/${id}`, {
      method: "PUT"
    });

    if (response.status === 200) {
      resultadoDiv.innerHTML = `<p style="color:green;">Exemplar ${id} marcado como danificado com sucesso!</p>`;
    } else if (response.status === 409) {
      resultadoDiv.innerHTML = `<p style="color:red;">Este exemplar está emprestado e não pode ser danificado.</p>`;
    } else if (response.status === 404) {
      resultadoDiv.innerHTML = `<p style="color:red;">Exemplar não encontrado.</p>`;
    } else {
      resultadoDiv.innerHTML = `<p style="color:red;">Erro ao processar o pedido.</p>`;
    }
  } catch (err) {
    resultadoDiv.innerHTML = `<p style="color:red;">Erro de conexão com o servidor.</p>`;
  }
});
