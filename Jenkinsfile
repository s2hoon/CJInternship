pipeline {
    agent any

    environment {
        AWS_ACCOUNT_ID = '051826731133'
        AWS_REGION = 'ap-northeast-2'
        REPOSITORY_NAME = 'cj-ecr'
        GIT_COMMIT = "${env.GIT_COMMIT}"
        IMAGE_TAG = "${GIT_COMMIT}"
        FULL_IMAGE_NAME = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${REPOSITORY_NAME}:${IMAGE_TAG}"
    }


    stages {
        stage('Checkout') {
            steps {
                echo "✔ Git 저장소에서 코드 체크아웃"
                checkout scm
            }
        }

        stage('Build App') {
            steps {
                echo "🛠 애플리케이션 빌드"
                sh '''
                    chmod +x gradlew
                    ./gradlew build
                '''
            }
        }

        stage('Docker Build') {
            steps {
                echo "🐳 Docker 이미지 빌드 및 태깅"
                sh """
                    docker build --no-cache -t ${REPOSITORY_NAME}:${IMAGE_TAG} .
                    docker tag ${REPOSITORY_NAME}:${IMAGE_TAG} ${FULL_IMAGE_NAME}
                """
            }
        }

        stage('Push to ECR') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-ecr-credentials']]) {
                    sh '''
                        aws ecr get-login-password --region $AWS_REGION | \
                        docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
                        docker push $FULL_IMAGE_NAME
                    '''
                }
            }
        }

        stage('Clean up Docker Images') {
            steps {
                echo "🧹 Docker 이미지 정리"
                sh '''
                    docker rmi ${REPOSITORY_NAME}:${IMAGE_TAG} ${FULL_IMAGE_NAME} || true
                '''
            }
        }
    }

    post {
        success {
            echo "✅ ECR 이미지 푸시 성공: ${FULL_IMAGE_NAME}"
        }
        failure {
            echo "❌ ECR 푸시 실패"
        }
    }
}
