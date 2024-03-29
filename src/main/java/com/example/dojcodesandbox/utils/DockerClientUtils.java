package com.example.dojcodesandbox.utils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.github.dockerjava.core.DockerClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 实现创建Docker容器
 */
@Slf4j
@Component
public class DockerClientUtils {
    /**
     * 连接docker服务器
     * @param dockerInstance
     * @return
     */
    public DockerClient connectDocker(String dockerInstance){
        DockerClient dockerClient = DockerClientBuilder.getInstance(dockerInstance).build();
        dockerClient.infoCmd().exec();
        return dockerClient;
    }



    /**
     * 创建容器
     * @param client
     * @return
     */
    public CreateContainerResponse createContainers(DockerClient client, String containerName, String imageName){

        CreateContainerResponse container = client.createContainerCmd(imageName)
                .withName(containerName)
                .exec();

        return container;

    }


    /**
     * 启动容器
     * @param client
     * @param containerId
     */
    public void startContainer(DockerClient client,String containerId){
        client.startContainerCmd(containerId).exec();
    }

    /**
     * 暂停容器
     * @param client
     * @param containerId
     */
    public void stopContainer(DockerClient client,String containerId){
        client.stopContainerCmd(containerId).exec();
    }

    /**
     * 删除容器
     * @param client
     * @param containerId
     */
    public void removeContainer(DockerClient client,String containerId){
        client.removeContainerCmd(containerId).exec();
    }

//
//    public static void main(String[] args) {
//        DockerClientUtils dockerClientUtils = new DockerClientUtils();
//        //连接Docker容器
//        DockerClient client = dockerClientUtils.connectDocker("tcp://118.25.27.171:2375");
//        //创建容器
//        CreateContainerResponse container = dockerClientUtils.createContainers(client,"sny_hello","hello-world");
//        //启动容器
//        dockerClientUtils.startContainer(client,container.getId());
//    }
}
