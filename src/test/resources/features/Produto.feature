# language: pt
Funcionalidade: Testar endpoints de produtos
  Como um usuário da API BioPure
  Quero cadastrar, listar, buscar, atualizar e excluir produtos
  Para garantir que o CRUD de produtos está funcionando corretamente

  Contexto:
    Dado que a API está rodando no ambiente de teste

  Cenário: Cadastrar produto com sucesso
    Dado que tenho um novo produto válido para cadastrar
    Quando envio a requisição de cadastro de produto
    Então o produto é cadastrado com sucesso e o status deve ser 201

  Cenário: Cadastrar produto inválido (sem nome)
    Dado que tento cadastrar um produto sem nome
    Quando envio a requisição de cadastro inválida
    Então a API deve retornar o status 400 e uma mensagem de erro

  Cenário: Listar todos os produtos
    Quando envio uma requisição GET de produto para "/api/produtos"
    Então o status da resposta de produto deve ser 200
    E o corpo da resposta deve conter uma lista de produtos

  Cenário: Buscar produto pelo nome
    Dado que existe um produto cadastrado com o nome "Copo biodegradável"
    Então o corpo da resposta deve conter o campo "nome" com valor "Copo biodegradável"

  Cenário: Atualizar produto existente
    Dado que tenho um produto existente para atualizar
    Quando envio uma requisição PUT para "/api/produtos"
    Então o status da resposta de produto deve ser 200
    E o corpo da resposta deve conter o campo "nome" com valor "Produto Atualizado"

  Cenário: Excluir produto existente
    Dado que tenho um produto existente
    Quando envio uma requisição DELETE para "/api/produtos"
    Então o status da resposta de produto deve ser 204
