async function buscarEmprestimo() {
  const usuarioId = document.getElementById("usuarioId").value.trim();

  if (usuarioId.length < 3) {
    alert("Digite um ID válido.");
    return;
  }

  try {
    const resposta = await fetch(`/emprestimo/ver?usuarioId=${usuarioId}`);
    
    if (!resposta.ok) {
      alert("Nenhum empréstimo encontrado ou erro no servidor.");
      return;
    }

    const emprestimo = await resposta.json();

    if (!emprestimo || !emprestimo.exemplar || emprestimo.exemplar.length === 0) {
      alert("Este usuário não tem empréstimos pendentes.");
      return;
    }

    const lista = document.getElementById("listaExemplares");
    lista.innerHTML = "";

    emprestimo.exemplar.forEach(e => {
      const div = document.createElement("div");
      div.classList.add("exemplar-item");
      div.textContent = `${e.id} - ${e.livro.nome}`;
      lista.appendChild(div);
    });

    document.getElementById("detalhesEmprestimo").classList.remove("hidden");
    lista.dataset.emprestimoId = emprestimo.id;

  } catch (e) {
    console.error("Erro ao buscar empréstimo:", e);
    alert("Erro ao buscar empréstimo.");
  }
}

async function confirmarDevolucao() {
  const usuarioId = document.getElementById("usuarioId").value.trim();

  if (!confirm("Tem certeza de que deseja devolver todos os exemplares?")) return;

  try {
    const resposta = await fetch(`/emprestimo/devolucao`, {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: `usuarioId=${encodeURIComponent(usuarioId)}`
    });

    if (!resposta.ok) {
      alert("Erro ao processar devolução.");
      return;
    }

    alert("Devolução registrada com sucesso!");
    window.location.reload();
  } catch (e) {
    console.error("Erro ao confirmar devolução:", e);
    alert("Erro ao registrar devolução.");
  }
}
