# jar包变化
ssh -i C:\Users\12101\.ssh\ubuntu20_server_ali.pem root@boer.ink " cd /root/elm;rm *.jar"
scp -i C:\Users\12101\.ssh\ubuntu20_server_ali.pem .\target\*.jar root@boer.ink:/root/elm

# 重启镜像
ssh -i C:\Users\12101\.ssh\ubuntu20_server_ali.pem root@boer.ink " cd /root/elm;docker compose down;docker compose build; docker compose up -d"
