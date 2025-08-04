document.addEventListener("DOMContentLoaded", () => {
  fetch("/editora/lista/json")
    .then(res => res.json())
    .then(editoras => {
      const select = document.getElementById("editoraId");
      editoras.forEach(ed => {
        const option = document.createElement("option");
        option.value = ed.id;
        option.textContent = ed.nome;
        select.appendChild(option);
      });
    });

  fetch("/area/lista/json")
    .then(res => res.json())
    .then(areas => {
      const select = document.getElementById("areaId");
      areas.forEach(area => {
        const option = document.createElement("option");
        option.value = area.id;
        option.textContent = area.nome;
        select.appendChild(option);
      });
    });

  fetch("/autor/lista/json")
    .then(res => res.json())
    .then(autores => {
      const container = document.getElementById("autores-container");
      autores.forEach(autor => {
        const label = document.createElement("label");
        label.innerHTML = `
          <input type="checkbox" name="autoresIds" value="${autor.id}">
          ${autor.nome}
        `;
        container.appendChild(label);
      });
    });
});

