# Iddog

## Finalidade
Esse aplicativo exibe uma listagem com imagens de cachorros de acordo com a raça escolhida.
Entre as opções de visualizações, estão husky, hound, pug e labrador. 

## Usabilidade
Na tela inicial de login, basta inserir um email válido para ter acesso as imagens.
A navegação pode variar entre as quatro opções disponíveis no menu.
Ao clicar em uma imagem específica, ela será aberta no tamanho original.

## Bibliotecas
+ Retrofit2
+ Rxjava
+ RxAdapter2  
+ Rxbinding3
+ Glide
+ Avi
+ Material Design
+ CardView
+ RecyclerView
+ Retrofit2 - converterGson
+ Okhttp3

###### Motivação para utilização das bibliotecas
+ Otimização no tempo de desenvolvimento.
+ Upload de imagens em cache para evitar disperdicio de memória e melhor performance de releitura - *Glide*
+ Simplicidade e eficiencia na criação de chamada de Web Service, o que possui muita complexidade nativo - *Retrofit2 | RxAdapter2 | Retrofit2 - converterGson | Okhttp3* 
+ Controle melhor e mais eficaz dos eventos com programação reativa - *Rxjava | Rxbinding3*
+ Layout customizado e elegante do progress loading - *Avi*

###### Arquitetura Utilizada
+ MVP
