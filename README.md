# SmartTask Secure 

O **SmartTask Secure** é um aplicativo Android para gerenciamento de tarefas pessoais e profissionais desenvolvido em Kotlin. 
O foco principal desta versão foi evoluir o aplicativo base aplicando camadas rígidas de segurança mobile, controle de acesso 
e conformidade com os princípios da Lei Geral de Proteção de Dados (LGPD). 

## Tecnologias e Bibliotecas Utilizadas
* **Linguagem:** Kotlin
* **IDE:** Android Studio
* **JetPack Security (Crypto):** Utilizado para criptografia de dados locais em repouso.
* **Android Keystore System:** Gerenciamento de chaves criptográficas em nível de hardware.

## Funcionalidades de Segurança Implementadas

### 1. Autenticação Obrigatória e Controle de Acesso 
* **Fluxo de Entrada:** Implementação da `LoginActivity`, que atua como barreira. O usuário não consegue visualizar ou gerenciar
tarefas sem se autenticar primeiro.
* **Prevenção de Bypass:** No `AndroidManifest.xml`, a `MainActivity` foi configurada como `android:exported="false"`. Isso
impede que outros aplicativos instalados no dispositivo forgem uma chamada para abrir a tela interna de tarefas burlando o logi.
* **Destruição de Sessão:** Ao realizar o Logout, o estado de autenticação é limpo e o usuário é jogado de volta à tela de login,
limpando a pilha de telas do sistema (`finish()`).

### 2. Criptografia de Dados (Proteção em Repouso)
* Em vez de salvar o estado de login em texto limpo usando o SharedPreferences comum, o projeto utiliza
**`EncryptedSharedPreferences`** (da biblioteca *Jetpack Security*).
* Os dados são criptografados usando o algoritmo **AES256_GCM** de forma transparente, protegendo as informações contra inspeção
de arqivos e engenharia reversa (mesmo em dispositivos com *root*).

## Análise de Riscos e LGPD (Privacy by Design)
O projeto foi concebido respeitando os direitos de privacidade do usuário: 
* **Minimização de Dados:** Coleta-se apenas o estritamente necessário (Nome de usuário e senha local) para a autenticação, sem
rastreamento abusivo ou coleta de dados excessivos.
* **Transaparência:** O aplicativo não exige permissões invasivas (como localização, contatos ou câmera) que não condizem com seu propósito de gerenciar tarefas.

---

## Como Executar o Projeto
1. Clone este repositório no seu computador:
   ```bash
   git clone [https://github.com/SEU_USUARIO/SmartTaskSecure.git](https://github.com/SEU_USUARIO/SmartTaskSecure.git)
