# Jenkins 配置指南

本文档说明如何在Jenkins中配置Android项目以构建APK文件。

## 前置要求

### 1. Jenkins 环境要求

- Jenkins 2.0+
- 安装了以下插件：
  - Git plugin
  - Pipeline plugin
  - Gradle plugin
  - Android Emulator plugin (可选)

### 2. 构建环境要求

Jenkins服务器需要安装以下软件：

```bash
# Java 8
sudo apt-get install openjdk-8-jdk

# Android SDK
wget https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip
unzip commandlinetools-linux-8512546_latest.zip -d /usr/local/android-sdk
export ANDROID_HOME=/usr/local/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# 安装Android SDK组件
sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.0"

# Gradle
wget https://services.gradle.org/distributions/gradle-7.5-bin.zip
unzip gradle-7.5-bin.zip -d /usr/local/
export GRADLE_HOME=/usr/local/gradle-7.5
export PATH=$PATH:$GRADLE_HOME/bin
```

## Jenkins 项目配置

### 1. 创建新的Pipeline项目

1. 登录Jenkins管理界面
2. 点击"新建任务"
3. 输入项目名称，选择"Pipeline"
4. 点击"确定"

### 2. 配置Git仓库

在项目配置页面：

1. **General** 标签页：
   - 勾选"丢弃旧的构建"
   - 设置保留天数：7天

2. **Pipeline** 标签页：
   - Definition: 选择"Pipeline script from SCM"
   - SCM: 选择"Git"
   - Repository URL: 输入您的GitLab仓库地址
   - Credentials: 配置Git访问凭据
   - Branch Specifier: `*/main` 或 `*/master`
   - Script Path: `Jenkinsfile`

### 3. 环境变量配置

在Jenkins系统配置中添加以下环境变量：

```
ANDROID_HOME=/usr/local/android-sdk
JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
GRADLE_HOME=/usr/local/gradle-7.5
PATH=${GRADLE_HOME}/bin:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${PATH}
```

### 4. 构建触发器配置

根据需要配置构建触发器：

- **Poll SCM**: 定期检查代码变更
- **GitHub hook trigger for GITScm polling**: GitHub/GitLab webhook
- **Build periodically**: 定时构建

## 构建流程说明

项目使用Jenkinsfile定义构建流程，包含以下阶段：

1. **Checkout**: 检出代码
2. **Setup Environment**: 设置构建环境
3. **Clean**: 清理项目
4. **Build Debug APK**: 构建Debug版本
5. **Build Release APK**: 构建Release版本
6. **Archive Artifacts**: 归档构建产物
7. **Generate Build Info**: 生成构建信息

## 构建产物

构建成功后，可以在Jenkins构建页面下载以下文件：

- `app-debug.apk`: Debug版本APK
- `app-release.apk`: Release版本APK
- `build-info.txt`: 构建信息文件

## 故障排除

### 常见问题

1. **Gradle权限问题**
   ```bash
   chmod +x gradlew
   ```

2. **Android SDK路径问题**
   - 确保ANDROID_HOME环境变量正确设置
   - 检查Android SDK组件是否完整安装

3. **Java版本问题**
   - 确保使用Java 8
   - 检查JAVA_HOME环境变量

4. **网络问题**
   - 确保Jenkins服务器能访问Maven中央仓库
   - 配置代理设置（如需要）

### 日志查看

在Jenkins构建页面可以查看详细的构建日志，帮助诊断问题。

## 自动化部署

可以扩展Jenkinsfile添加部署阶段：

```groovy
stage('Deploy') {
    steps {
        echo '部署到测试环境...'
        // 添加部署脚本
    }
}
```

## 联系支持

如遇到问题，请检查：
1. Jenkins系统日志
2. 构建控制台输出
3. 环境变量配置
4. 网络连接状态 