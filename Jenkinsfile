pipeline {
    agent any
    
    environment {
        ANDROID_HOME = '/usr/local/android-sdk'
        JAVA_HOME = '/usr/lib/jvm/java-8-openjdk'
        GRADLE_HOME = '/usr/local/gradle'
        PATH = "${GRADLE_HOME}/bin:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '正在检出代码...'
                checkout scm
            }
        }
        
        stage('Setup Environment') {
            steps {
                echo '正在设置构建环境...'
                sh '''
                    echo "Java版本:"
                    java -version
                    echo "Gradle版本:"
                    ./gradlew --version
                    echo "Android SDK位置: $ANDROID_HOME"
                '''
            }
        }
        
        stage('Clean') {
            steps {
                echo '正在清理项目...'
                sh './gradlew clean'
            }
        }
        
        stage('Build Debug APK') {
            steps {
                echo '正在构建Debug版本APK...'
                sh './gradlew assembleDebug'
            }
        }
        
        stage('Build Release APK') {
            steps {
                echo '正在构建Release版本APK...'
                sh './gradlew assembleRelease'
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                echo '正在归档构建产物...'
                archiveArtifacts artifacts: 'app/build/outputs/apk/**/*.apk', fingerprint: true
            }
        }
        
        stage('Generate Build Info') {
            steps {
                echo '正在生成构建信息...'
                script {
                    def buildInfo = """
构建信息:
- 构建编号: ${env.BUILD_NUMBER}
- 构建时间: ${new Date().format("yyyy-MM-dd HH:mm:ss")}
- Git提交: ${env.GIT_COMMIT ?: 'N/A'}
- 分支: ${env.GIT_BRANCH ?: 'N/A'}
- APK文件:
  - Debug: app/build/outputs/apk/debug/app-debug.apk
  - Release: app/build/outputs/apk/release/app-release.apk
"""
                    writeFile file: 'build-info.txt', text: buildInfo
                    archiveArtifacts artifacts: 'build-info.txt'
                }
            }
        }
    }
    
    post {
        always {
            echo '构建完成，清理工作空间...'
            cleanWs()
        }
        success {
            echo '✅ 构建成功！APK文件已生成并归档。'
        }
        failure {
            echo '❌ 构建失败，请检查日志。'
        }
    }
} 