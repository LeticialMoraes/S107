

echo "Pipeline executado!"

# Verificando se o e-mail para notificação está disponível como variável de ambiente
if [ -z "$EMAIL_NOTIFICATION" ]; then
  echo "A variável de ambiente EMAIL_NOTIFICATION não foi configurada."
  exit 1
fi

# Instalando o cliente de e-mail
sudo apt-get update
sudo apt-get install -y mailutils

# Enviando um e-mail com informações sobre a execução do pipeline
echo "Pipeline executado com sucesso!" | mail -s "GitHub Actions Pipeline Notification" "$EMAIL_NOTIFICATION"
