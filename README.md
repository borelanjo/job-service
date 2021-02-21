# Job Service
Projeto está em um momento inicial. 

A ideia dele seria ter uma fila no Kafka pra envio de comando para iniciar um job e outra para receber quando o job foi finalizado.
Vou criar um módulo de exemplo de como utilizar.

## Tecnologias

- Kafka;
- Java e Spring Boot;
- Docker;
- Postgres;

## Requisitos

- Endpoints para CRUD de tipos de Job(Criar, Listar, Atualizar ou desativar um tipo);
- Endpoint para Listagem de Jobs com opção de filtro por Status, Tipo;
- Endpoint para execução manual de Jobs;
    - Endpoint para mudança de status de Jobs mortos;
- Endpoint para agendamento de execução de Jobs;

## Funcionamento

1. Deve ser cadastrado tipos de job (ex.: `GENERATE_RANDOM_NUMBER_JOB`);
2. Deve ser dado o comando de execução de job (manualmente ou através de agendamento);
3. Job será enviado para um tópico do Kafka de `requisição de execução de jobs`;
4. A aplicação que sabe da existência desse job deve ter um tratamento por group-id para só considerar o job que tiver necessidade;
    - Não importa o número de consumidores, como a key vai ser única o job só será executa 1x por comando;
    - Caso já exista um job daquele tipo em execução poderá ser mandado para a fila de resposta que já existe um job em execução;
5. Ao job ser finalizado a aplicação deve mandar um comando do termino;
6. O job deve ser atualizado como finalizado;