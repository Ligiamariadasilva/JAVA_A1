Simulador de Corrida Avançado em Java

1. Informações Gerais sobre o Projeto
Breve Descrição do Projeto
Este é um projeto de um simulador de corridas totalmente baseado em texto (console), desenvolvido em Java. Ele foi construído para aplicar e demonstrar conceitos fundamentais e avançados da Programação Orientada a Objetos (POO), como herança, polimorfismo, encapsulamento e o uso de interfaces.

O sistema permite ao usuário não apenas gerenciar os componentes de um campeonato (equipes, pilotos e veículos), mas também participar de uma simulação de corrida dinâmica e imprevisível, com múltiplas etapas, mudanças climáticas e eventos aleatórios.

Objetivos e Funcionalidades Principais
O objetivo principal é criar uma aplicação robusta e bem estruturada, seguindo os princípios do Clean Code e da arquitetura MVC (Model-View-Controller).

As funcionalidades incluem:

Gerenciamento Completo (CRUD):

Criar, Ler, Editar e Deletar Equipes.

Criar, Ler, Editar e Deletar Pilotos, associando-os a uma equipe.

Criar, Ler, Editar e Deletar Veículos (Carro ou Moto), associando-os a um piloto e a um tipo de pneu.

Persistência de Dados:

Todas as informações de equipes, pilotos e veículos são salvas em arquivos (.ser) usando a serialização nativa do Java, garantindo que os dados não se percam ao fechar o programa.

Simulação de Corrida Dinâmica:

Execução de uma corrida com 4 etapas.

O clima muda entre as etapas, afetando diretamente o desempenho dos veículos.

Ocorrência de imprevistos aleatórios (ex: pneu furado, problema no motor) que podem alterar drasticamente o resultado.

Pit Stops interativos, permitindo ao jogador tomar decisões estratégicas sobre a troca de pneus.

Sistema de Log:

Registro de todas as ações importantes e erros em um arquivo system_activity.log para facilitar a depuração e o rastreamento de atividades.

2. Informações sobre as Classes e suas Relações
A arquitetura do projeto foi pensada para ser modular e coesa. As relações entre as classes são a base para o funcionamento do sistema.


Um diagrama conceitual das principais relações de classes.

Explicação das Relações
Herança (extends):

Veiculo -> Carro, Moto: Veiculo é uma classe abstrata que define um "contrato" base para qualquer veículo. Carro e Moto herdam seus atributos (como piloto e pneu) e são obrigados a implementar o método calcularDesempenho de sua própria maneira.

Pneu -> PneuSeco, PneuChuva, PneuNeve: Similarmente, Pneu é uma classe abstrata que define que todo pneu deve ter um getTipo(). As classes filhas concretizam essa definição.

Implementação (implements):

GaragemController implements Oficina: A interface Oficina define um contrato que diz: "quem quiser ser uma oficina, precisa saber trocarPneu e realizarManutencao". O GaragemController assina esse contrato, garantindo que ele cumpra essas funções. Isso promove um baixo acoplamento.

Associação:

Piloto e Equipe: Um Piloto está associado a uma Equipe. A classe Piloto armazena o equipeId como uma referência. É uma relação do tipo "tem-um", mas mais fraca que a composição.

Agregação (Um tipo de Associação):

Veiculo e Piloto: Um Veiculo tem um Piloto. O objeto Piloto é passado para o Veiculo no momento da sua criação. O Veiculo não "possui" o piloto no sentido de que o piloto pode existir independentemente do veículo (ele pode, por exemplo, ser atribuído a outro veículo). Se o veículo for deletado, o piloto não é.

Composição (Um tipo de Associação forte):

Corrida e Resultado: Um Resultado é parte de uma Corrida. O objeto Resultado só faz sentido no contexto da corrida em que foi gerado. Se a Corrida for deletada, os seus Resultados perdem o sentido e devem ser deletados também. O ciclo de vida do Resultado está atrelado ao da Corrida.

3. Como Executar o Projeto
Siga os passos abaixo para compilar e executar a aplicação via linha de comando.

Pré-requisitos
JDK (Java Development Kit): Versão 11 ou superior instalada e configurada nas variáveis de ambiente do seu sistema.

Instruções de Execução
Navegue até a Pasta src: Abra o terminal ou prompt de comando e navegue até a pasta src do projeto.

cd caminho/para/seu/projeto/src

Compile o Código: Execute o comando abaixo para compilar todos os arquivos .java e colocar os arquivos .class gerados em uma pasta bin na raiz do projeto.

# No Windows (usando ponto e vírgula)
javac -d ../bin corrida/views/MainView.java

# No Linux/macOS (usando dois pontos)
javac -d ../bin corrida/views/MainView.java

Este comando compila o ponto de entrada principal, e o compilador se encarrega de encontrar e compilar todas as outras classes necessárias.

Execute a Aplicação: Após a compilação, volte para a pasta raiz do projeto (ou execute a partir de qualquer lugar usando o classpath correto) e inicie o programa.

# Navegue para a pasta raiz (um nível acima de 'src')
cd ..

# Execute o programa
java -cp bin corrida.views.MainView

O comando -cp bin diz ao Java para procurar os arquivos .class compilados dentro da pasta bin.

A aplicação será iniciada e o menu principal será exibido no console.

4. Uso de IA no Desenvolvimento
Uma Inteligência Artificial (IA), especificamente o modelo Gemini do Google, foi utilizada como uma ferramenta de assistência e aceleração durante todo o ciclo de desenvolvimento deste projeto. A IA não escreveu o projeto sozinha, mas atuou como um "programador em par" (pair programmer), um mentor e um consultor técnico.

As principais contribuições da IA foram:

Estruturação e Arquitetura: Auxílio na definição da estrutura de pacotes (MVC) e na criação do esqueleto inicial das classes, garantindo uma base sólida e organizada.

Geração de Código Boilerplate: Criação rápida de código repetitivo, como os métodos CRUD nos controllers e a lógica de leitura/escrita nos repositórios, permitindo que o foco humano fosse direcionado para a lógica de negócio mais complexa.

Refatoração e Implementação de Novas Features: Sugestão de melhorias, como a transição da persistência em JSON para a serialização nativa, e auxílio na implementação de funcionalidades complexas, como o sistema de etapas e imprevistos na corrida.

Depuração (Debugging): Ajuda na identificação e correção de erros lógicos e de compilação, como o problema de escopo da exceção na MainView, explicando a causa raiz do problema e propondo a solução correta.

Documentação: Geração de explicações detalhadas sobre o código, conceitos de POO aplicados e a criação deste próprio arquivo README.md.

O uso da IA permitiu um desenvolvimento mais rápido, didático e com uma qualidade de código superior, servindo como uma ferramenta poderosa para aprendizado e produtividade.

5. Referências e Recursos
Documentação Oficial do Java SE 17:

Serializable (Java Platform SE 17)

ObjectInputStream (Java Platform SE 17)

Optional (Java Platform SE 17)
