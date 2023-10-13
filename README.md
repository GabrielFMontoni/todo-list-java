
<div style="display: flex;" align="center"><br>
<h1>todo-list-java</h1>
Uma lista de tarefas usando Spring Boot e Java. Através de métodos REST e um banco de dados h2 que inicializa localmente, podemos realizar cadastro de usuários, autenticação e cadastro de tarefas.
  <br>
<h4>Em um curso da RocketSeat, construí uma todolist em Java utilizando o framework Spring.</h4>
</div>



##  :hammer: Funcionalidades do projeto
Em uma ferramenta para métodos REST como o Apidog, você pode realizar cadastros e verificação de usuários e tarefas. <br>
Para cadastrar um usuário, coloque um método POST com a url: https://todolist-gabrielmontoni.onrender.com/users/ <br>
No corpo da requisição, passe um objeto com "name", "username" e "password", e se lembre das credenciais para autenticar quando for cadastrar uma tarefa.<br>

Para cadastrar uma tarefa, use o método POST com a url: https://todolist-gabrielmontoni.onrender.com/tasks/ <br>
Coloque na parte de autenticação, as credenciais cadastradas, e no corpo da requisição, adicione um objeto com as seguintes chaves:<br>
"description", "title", "startAt", "endAt", "priority". <br>
O formato da data deve ser yyyy-mm-ddTHH:mm-ss <br>

Pode utilizar o método GET para verificar as tarefas cadastradas no usuário autenticado com: <br>
https://todolist-gabrielmontoni.onrender.com/tasks/ <br>

E a ultima funcionalidade implementada é a de alteração dessa tarefa <br>
Pegue o ID da tarefa que foi cadastrada e coloque na URL, ficando: <br>
https://todolist-gabrielmontoni.onrender.com/tasks/IDTAREFA <br>
Com uma requisição do tipo PUT, altere alguma propriedade, passando a propriedade que deseja alterar da tarefa, como "title". <br>
Lembre-se de estar autenticado para isto! <br>

## :file_folder: Link da URL para requisições
https://todolist-gabrielmontoni.onrender.com/


## Tecnologia Utilizada
<div style="display: inline_block" align="center"><br>
  <center><img align="center" alt="HTML" height="100" width="100" src="https://logospng.org/download/java/logo-java-4096.png">
  <img align="center" alt="CSS" height="100" width="100" src="https://github.com/GabrielFMontoni/todo-list-java/assets/121250213/d588bdd6-67de-409e-b497-72fc17a2ec5d">
  </center>

</div>
