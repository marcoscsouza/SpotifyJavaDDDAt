
# 1. Tipos de Operações

O programa lida com tipos de operações, decidindo qual de acordo com a linha que está sendo processada.

## Criação de Conta

- **Método**: `Usuario.criar(nome, email, senha, cpf, cartao e plano)`
- **Endpoint**: `POST localhost:8080/usuario`
- **Body**:
  ```json
  {
      "nome": "Marcos",
      "senha": "123456",
      "cpf": "13209711054",
      "email": "marcos@gmail.com",
      "numeroCartao": "1231121231",
      "limite": "1000",
      "cartaoAtivo": "true",
      "idPlano": "089eea14-4989-46ba-b370-dac88dbdb98b"
  }
  
- **Return**:
  - **STATUS**: 201 Created
  - **Body**:
    ```json
    {
        "id": "0b91042b-5f4f-462b-a84e-80745e9657e2",
        "nome": "Marcos",
        "email": "marcos@gmail.com",
        "senha": "********",
        "cpf": "13209711054",
        "numeroCartao": "**** **** **** ****",
        "limite": 976.55,
        "cartaoAtivo": true,
        "idPlano": "089eea14-4989-46ba-b370-dac88dbdb98b"
    }
    ```

## Autorização de Transação

- **Método**: `Cartao.ValidarTransacao(transacao)`
- **Verifica as seguintes ocorrências**:
  - Quantidade de transações a cada dois minutos, se está em alta frequência.
  - Quantidade de transações a cada dois minutos, se está duplicada.
  - Se valor da transação não for maior que o valor limite.

- **Return**:
  - Exceptions

## Favoritar Músicas

- **Método**: `Playlist.adicionarMusica(musica)`
- **Endpoint**: `POST localhost:8080/usuario/{playlistId}/musica/{musicaId}`
- **Return**: Status OK 200

- **Método**: `Playlist.removerMusica(musica)`
- **Endpoint**: `DELETE localhost:8080/usuario/{playlistId}/musica/{musicaId}`
- **Return**: Status OK 200

## Playlist

- **Método**: `criarPlaylist(playlist)`
- **Endpoint**: `POST localhost:8080/usuario/playlists`
- **Return**: Status OK 200

## Assinatura

- **Método**: `Assinatura.cadastrarAssinatura(assinatura)`
- **Endpoint**: `POST localhost:8080/usuario/assinaturas`
- **Return**: Status OK 200

# 2. Violações da Lógica de Negócios para Antifraude

Você deve implementar as seguintes regras, tendo em mente que novas regras aparecerão no futuro:

## O usuário pode ter somente um plano ativo

- Esta validação ocorre por meio do CPF, só pode existir uma conta por CPF no aplicativo.
- O método se encontra dentro da classe `Usuario` com nome de `isValid()`.

## O usuário deve ter um cartão de crédito válido

- Esta validação ocorre por meio da data de validade, limite do cartão e se está ativo.
- Todos estes métodos estão dentro da classe `Cartao`.

## Regras de Transações

- Não deve haver mais de 3 transações em um intervalo de 2 minutos: **alta-frequência-pequeno-intervalo**
- Não deve haver mais de 2 transações semelhantes (mesmo valor e comerciante) em um intervalo de 2 minutos: **transação duplicada**
- Estas duas validações ocorrem dentro da classe `Cartao` no método `validarTransacao`.

## Princípios S.O.L.I.D.

- **Princípio de Responsabilidade Única**: temos a separação de responsabilidades entre Services, Repository e Controller.
- **Princípio de Segregação de Interfaces**: evita ter métodos desnecessários.
- **Princípio Aberto/Fechado**: Sendo aberto para extensão, porém fechado para modificação.

## Boas Práticas

- O projeto foi dividido utilizando o princípio de subdomínios em duas partes:
  - **Music**: responsável pela lógica de gestão das músicas.
  - **User**: para lógica de gestão dos usuários.

  (Partnership, Customer-Supplier ou Anticorruption Layer)

- Utilizar os Domain Services, Aggregates e Context Mapping para comunicação do domínio e modelagem do domínio.
- Além da divisão em subdomínios ainda foi utilizado o princípio de Bounded Contexts.

## Bounded Contexts de Music

- **Entities**: Banda, Musica e Playlist.
- **Services**: BandaService, MusicaService e PlaylistService.
- **Controllers**: MusicaController e PlaylistController.

## Bounded Contexts de User

- **Entities**: Assinatura, Cartao, Plano, Transacao e Usuario.
- **Services**: AssinaturaService e UsuarioService.
- **Controllers**: AssinaturaController e UsuarioController.

Realizando estas divisões conseguimos ter maior `manutenibilidade`, `escalabilidade` e melhor `coesão` em todo o nosso código, permitindo assim futuras manutenções e adições de conteúdo.

Possuímos além disso dois Aggregates root: `Usuario` e `Playlist`.
