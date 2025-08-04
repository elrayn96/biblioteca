let usuarioSelecionado = null;
let exemplaresSelecionados = [];
const maxExemplares = 5;

const inputUsuario = document.getElementById('usuario');
const sugestoesUsuario = document.getElementById('sugestoes-usuario');

inputUsuario.addEventListener('input', async function () {
  const termo = this.value.trim();
  sugestoesUsuario.innerHTML = '';
  if (termo.length < 3) return;

  try {
    const response = await fetch(`/emprestimo/usuarios-disponiveis?query=${termo}`);
    const usuarios = await response.json();

    if (usuarios.length === 0) {
      sugestoesUsuario.innerHTML = '<p class="erro-sugestao">Nenhum usuário disponível</p>';
    } else {
      usuarios.forEach(u => {
        const p = document.createElement('p');
        p.textContent = `${u.id} - ${u.nome}`;
        p.classList.add('sugestao');
        p.addEventListener('click', () => {
          inputUsuario.value = `${u.id} - ${u.nome}`;
          usuarioSelecionado = u;
          sugestoesUsuario.innerHTML = '';
        });
        sugestoesUsuario.appendChild(p);
      });
    }
  } catch (err) {
    sugestoesUsuario.innerHTML = '<p class="erro-sugestao">Erro ao buscar usuários</p>';
  }
});

const inputExemplar = document.getElementById('exemplar');
const sugestoesExemplar = document.getElementById('sugestoes-exemplar');
const listaExemplares = document.getElementById('exemplares-selecionados');

inputExemplar.addEventListener('input', async function () {
  const termo = this.value.trim();
  sugestoesExemplar.innerHTML = '';
  if (termo.length < 3) return;

  try {
    const response = await fetch(`/emprestimo/exemplares-disponiveis?query=${termo}`);
    const exemplares = await response.json();

    exemplares.forEach(e => {
      const p = document.createElement('p');
      p.textContent = `${e.id} - ${e.livro.nome}`;
      p.classList.add('sugestao');
      p.addEventListener('click', () => {
        adicionarExemplar(e);
        inputExemplar.value = '';
        sugestoesExemplar.innerHTML = '';
      });
      sugestoesExemplar.appendChild(p);
    });
  } catch (err) {
    sugestoesExemplar.innerHTML = '<p class="erro-sugestao">Erro ao buscar exemplares</p>';
  }
});

function tentarAdicionarExemplar() {
  const termo = inputExemplar.value.trim();
  if (termo.length < 3) {
    alert("Digite pelo menos 3 caracteres para buscar o exemplar.");
    return;
  } 
  fetch(`/emprestimo/exemplares-disponiveis?query=${termo}`)
    .then(res => res.json())
    .then(exemplares => {
      if (exemplares.length > 0) {
        adicionarExemplar(exemplares[0]); // pega o primeiro resultado
        inputExemplar.value = '';
        sugestoesExemplar.innerHTML = '';
      } else {
        alert("Nenhum exemplar disponível encontrado.");
      }
    })
    .catch(() => alert("Erro ao buscar exemplar."));
}


function adicionarExemplar(exemplar) {
  if (exemplaresSelecionados.length >= maxExemplares) {
    alert('Você só pode adicionar até ' + `${maxExemplares}` + ' exemplares.');
    return;
  }

  if (exemplaresSelecionados.some(e => e.livro.id === exemplar.livro.id)) {
    alert('Você não pode adicionar dois exemplares do mesmo livro.');
    return;
  }

  exemplaresSelecionados.push(exemplar);
  renderizarExemplares();
}

function renderizarExemplares() {
  listaExemplares.innerHTML = '';
  exemplaresSelecionados.forEach((e, index) => {
    const tag = document.createElement('div');
    tag.classList.add('tag-exemplar');
    tag.textContent = `${e.id} - ${e.livro.nome}`;
    const remover = document.createElement('span');
    remover.textContent = 'x';
    remover.classList.add('remover-tag');
    remover.onclick = () => {
      exemplaresSelecionados.splice(index, 1);
      renderizarExemplares();
    };
    tag.appendChild(remover);
    listaExemplares.appendChild(tag);
  });
}

function prepararEnvio() {
  if (!usuarioSelecionado) {
    alert('Por favor selecione um usuário.');
    return false;
  }
  if (exemplaresSelecionados.length === 0) {
    alert('Selecione ao menos um exemplar.');
    return false;
  }

  document.querySelectorAll('input[type="hidden"]').forEach(el => el.remove());


  const inputHiddenUsuario = document.createElement('input');
  inputHiddenUsuario.type = 'hidden';
  inputHiddenUsuario.name = 'usuarioId';
  inputHiddenUsuario.value = usuarioSelecionado.id;
  document.querySelector('form').appendChild(inputHiddenUsuario);

  exemplaresSelecionados.forEach(exemplar => {
    const inputHiddenExemplar = document.createElement('input')
    inputHiddenExemplar.type = 'hidden';
    inputHiddenExemplar.name = 'exemplarIds';
    inputHiddenExemplar.value = exemplar.id;
    document.querySelector('form').appendChild(inputHiddenExemplar);
  });

  return true;
}