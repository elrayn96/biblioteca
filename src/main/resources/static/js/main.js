const menuSelect = document.getElementById('menu-select');
const conteudoMenu = document.getElementById('conteudo-menu');

const criarBotao = (texto, link) => {
  return `<button onclick="location.href='${link}'">${texto}</button>`;
};

const menus = {
  "menu-usuarios": `
    <h2>Menu Usuários</h2>
    ${criarBotao("Cadastrar Estudante", "/usuarios/estudante")}
    ${criarBotao("Cadastrar Docente", "/usuarios/docente")}
    ${criarBotao("Cadastrar Funcionário", "/usuarios/funcionario")}
    ${criarBotao("Dados de um Usuário", "/usuarios/ver")}
    ${criarBotao("Listar Usuários (ID)", "/usuarios/lista/id")}
    ${criarBotao("Listar Usuários (Alfabética)", "/usuarios/lista/nome")}
  `,
  "menu-livros": `
    <h2>Menu Livros</h2>
    ${criarBotao("Cadastrar Livro", "/livro/cadastrar")}
    ${criarBotao("Livros de um Autor", "/livro/por-autor")}
    ${criarBotao("Ver Livro", "/livro/ver")}
    ${criarBotao("Listar por ID", "/livro/lista/id")}
    ${criarBotao("Listar por Nome", "/livro/lista/nome")}
  `,
  "menu-exemplares": `
    <h2>Menu Exemplares</h2>
    ${criarBotao("Cadastrar Exemplar", "/exemplar/cadastrar")}
    ${criarBotao("Reportar Danificado", "/exemplar/danificar")}
    ${criarBotao("Listar Exemplares Disponiveis (De um livro)", "/exemplar/disponiveis")}
    ${criarBotao("Listar Exemplares Danificados (De um Livro)", "/exemplar/danificados")}
  `,
  "menu-areas": `
    <h2>Menu Áreas de Conhecimento</h2>
    ${criarBotao("Cadastrar Área", "/area/cadastrar")}
    ${criarBotao("Listar por ID", "/area/lista/id")}
    ${criarBotao("Listar por Nome", "/area/lista/nome")}
  `,
  "menu-autores": `
    <h2>Menu Autores</h2>
    ${criarBotao("Cadastrar Autor", "/autor/cadastrar")}
    ${criarBotao("Ver Autor", "/autor/ver")}
    ${criarBotao("Listar por ID", "/autor/lista/id")}
    ${criarBotao("Listar por Nome", "/autor/lista/nome")}
  `,
  "menu-editoras": `
    <h2>Menu Editoras</h2>
    ${criarBotao("Cadastrar Editora", "/editora/cadastrar")}
    ${criarBotao("Ver Editora", "/editora/ver")}
    ${criarBotao("Listar Editoras por ID", "/editora/lista/id")}
    ${criarBotao("Listar Editoras por Nome", "/editora/lista/nome")}
  `,
  "menu-emprestimos": `
    <h2>Menu Empréstimos</h2>
    ${criarBotao("Registrar Empréstimo", "/emprestimo/cadastrar")}
    ${criarBotao("Registrar Devolução", "/emprestimo/devolucao")}
  `
};

menuSelect.addEventListener('change', () => {
  const selected = menuSelect.value;
  conteudoMenu.innerHTML = selected && menus[selected] ? menus[selected] : '';
});


