# language: pt
Funcionalidade: Testar endpoint de usuários
  Como um usuário da API BioPure
  Quero cadastrar e listar usuários
  Para garantir que o CRUD básico funciona corretamente

  Contexto:
    Dado que a API de usuários está rodando no ambiente de teste

  Cenário: Cadastrar usuário com sucesso
    Dado que tenho um novo usuário válido para cadastrar
    Quando envio a requisição de cadastro de usuário
    Então o usuário é cadastrado com sucesso e o status deve ser 201

  Cenário: Listar todos os usuários
    Quando envio uma requisição GET para "/api/usuarios"
    Então o status da resposta deve ser 200
