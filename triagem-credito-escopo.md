# Mini projeto: Sistema de Triagem de Pedidos de Crédito

**Contexto:** a "FinTech Paraíba" precisa de um programa de console em Java que receba pedidos de empréstimo, valide os dados, calcule um score e decida se o pedido é aprovado, reprovado ou vai para análise manual. Sem banco de dados, sem framework: só Java puro e console.

**O que você vai treinar:** lógica de programação (condicionais, laços, métodos), tratamento de dados (validação, normalização, coleções) e tomada de decisão (regras em cascata, prioridade de critérios).

---

## Dados de entrada de cada pedido

- Nome completo
- CPF
- Idade
- Renda mensal
- Valor solicitado
- Quantidade de parcelas
- Possui dívidas ativas (sim/não)
- Tempo de emprego em meses

---

## Regras de negócio

### 1. Validação dos dados (tratamento)

- **RN01:** nome não pode ser vazio, nem conter apenas espaços. Espaços sobrando no início, fim e entre palavras devem ser removidos, e o nome deve ser padronizado (ex.: capitalização uniforme).
- **RN02:** CPF deve ser aceito com ou sem pontuação, mas armazenado só com números. Deve ter 11 dígitos, não pode ter todos os dígitos iguais e deve passar pela verificação dos dígitos verificadores.
- **RN03:** idade deve ser um número inteiro. Menores de 18 anos são inválidos.
- **RN04:** renda mensal e valor solicitado devem ser maiores que zero.
- **RN05:** parcelas devem estar entre 6 e 60.
- **RN06:** tempo de emprego não pode ser negativo.
- **RN07:** entradas não numéricas em campos numéricos não podem derrubar o programa; o usuário deve ser avisado e poder digitar de novo.
- **RN08:** CPF duplicado na mesma execução deve ser rejeitado como pedido repetido.

### 2. Cálculo de indicadores

- **RN09:** valor da parcela = valor solicitado dividido pelo número de parcelas (sem juros, para simplificar).
- **RN10:** comprometimento de renda = valor da parcela dividido pela renda mensal, em percentual.

### 3. Reprovação automática (têm prioridade sobre o score)

- **RN11:** comprometimento de renda acima de 40% → reprovado.
- **RN12:** idade acima de 75 anos ao final do prazo do financiamento → reprovado.
- **RN13:** valor solicitado acima de 20 vezes a renda mensal → reprovado.

### 4. Score (0 a 100)

O score começa em 50 e sofre ajustes:

- **RN14:** comprometimento até 20% → +20; entre 20% e 30% → +10; entre 30% e 40% → 0.
- **RN15:** tempo de emprego: menos de 6 meses → -15; de 6 a 24 meses → +5; acima de 24 meses → +15.
- **RN16:** possui dívidas ativas → -25.
- **RN17:** idade entre 25 e 55 anos → +10.
- **RN18:** o score final nunca pode ficar abaixo de 0 nem acima de 100.

### 5. Decisão final

- **RN19:** score ≥ 70 → **Aprovado**.
- **RN20:** score entre 40 e 69 → **Análise manual**.
- **RN21:** score < 40 → **Reprovado**.
- **RN22:** toda decisão deve exibir o motivo (qual regra pesou), não só o resultado.

---

## Critérios de aceite

### Entrada e validação

- **CA01:** ao digitar um CPF inválido, o sistema informa o erro e pede o dado novamente, sem encerrar.
- **CA02:** ao digitar texto em campo numérico, o sistema não quebra e solicita novamente.
- **CA03:** CPF digitado como "123.456.789-09" e "12345678909" é tratado como o mesmo.
- **CA04:** nome digitado com espaços extras e caixa mista aparece padronizado no resultado.
- **CA05:** dois pedidos com o mesmo CPF na mesma execução: o segundo é recusado com mensagem clara.

### Regras de decisão

- **CA06:** um pedido que viole qualquer reprovação automática é reprovado mesmo que o score calculado fosse alto.
- **CA07:** o score nunca aparece fora do intervalo de 0 a 100.
- **CA08:** valores exatamente nas fronteiras (20%, 30%, 40% de comprometimento; 6 e 24 meses; score 40 e 70) caem na faixa correta, conforme descrito nas regras.
- **CA09:** todo resultado mostra: nome, CPF formatado, parcela, comprometimento, score, decisão e motivo.

### Fluxo do programa

- **CA10:** o programa permite cadastrar vários pedidos em sequência, perguntando ao final de cada um se deseja continuar.
- **CA11:** ao encerrar, exibe um resumo: total de pedidos, quantidade de aprovados, em análise e reprovados, média de score e maior valor solicitado entre os aprovados.
- **CA12:** o resumo lida corretamente com o caso de nenhum pedido aprovado (sem divisão por zero ou resultado estranho).

### Qualidade de código (para o seu estudo)

- **CA13:** nenhuma regra de negócio fica misturada com leitura de dados do console: validação, cálculo e decisão ficam separados.
- **CA14:** os valores das faixas (40%, 70, 6 parcelas etc.) não ficam "soltos" espalhados pelo código.
- **CA15:** cada regra pode ser testada isoladamente com casos que você mesmo define.

---

## Roteiro de estudo sugerido

1. Primeiro faça só a entrada e validação (RN01 a RN08).
2. Depois os cálculos e a reprovação automática.
3. Em seguida o score e a decisão.
4. Por fim o resumo final e a organização do código.

## Desafios extras (opcional)

- Permitir configurar juros mensais e recalcular a parcela.
- Ordenar o relatório final por score.
- Criar uma regra nova de sua autoria e documentar o impacto nas decisões.

---

## Extensão opcional: versão Spring Boot

Recomendação: faça primeiro em Java puro e só depois migre, reaproveitando as classes de regra. As regras RN01 a RN22 não mudam; muda a casca ao redor (API REST no lugar do console, erros HTTP no lugar do "digite novamente", camadas controller e service).

- **CA16:** todo erro de validação retorna status HTTP adequado e lista todos os campos inválidos de uma vez, não só o primeiro.
- **CA17:** a decisão é devolvida em formato estruturado, com score, parcela, comprometimento e motivo.
- **CA18:** a lógica de decisão funciona e é testável sem subir o servidor (teste unitário direto na regra).
- **CA19:** pelo menos um teste de integração cobre o fluxo completo: pedido válido, pedido inválido e CPF repetido.
- **CA20:** as faixas e limites (40%, 70, 6 parcelas etc.) ficam em configuração, não espalhadas pelo código.
- **CA21:** o resumo final vira um endpoint de consulta, com o mesmo tratamento do CA12 para quando não há aprovados.
