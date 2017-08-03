echo "-----------------------------------------------------"
echo "Installing and starting ewt-ms container ..."
echo "-----------------------------------------------------"

docker config-ctn .
docker rm config-ctn .
docker rmi config-img .
docker build -t config-img .
docker run --name config-ctn -d -p 8081:8081 config-img .