const button = document.getElementById("adicionar")
const modal = document.querySelector("dialog");
const buttonClose = document.getElementById("fechar");
const buttonSubmit = document.getElementById("submit")

button.onclick = function(){
  modal.showModal();
}

buttonClose.onclick = function(){
  modal.close();
}

buttonSubmit.onclick = function(){
  window.alert("Cadastrado com sucesso.");
}


