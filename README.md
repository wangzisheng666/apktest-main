<<<<<<< HEAD
# Songjia HelloWorld Android 应用

这是一个简单的Android HelloWorld应用，用于演示Jenkins CI/CD构建流程。

## 项目结构

```
testapk/
├── app/                    # Android应用模块
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/      # Java源代码
│   │   │   ├── res/       # 资源文件
│   │   │   └── AndroidManifest.xml
│   │   └── test/          # 测试代码
│   └── build.gradle       # 应用级构建配置
├── gradle/                 # Gradle包装器
├── build.gradle           # 项目级构建配置
├── settings.gradle        # 项目设置
├── gradlew               # Gradle包装器脚本
├── gradlew.bat           # Windows Gradle包装器脚本
└── Jenkinsfile           # Jenkins CI/CD配置
```

## 构建要求

- Android SDK
- Gradle 7.0+
- Java 8+

## 本地构建

```bash
# 克隆项目
git clone <your-gitlab-repo-url>
cd testapk

# 构建APK
./gradlew assembleDebug

# 构建发布版本
./gradlew assembleRelease
```

## Jenkins构建

项目包含Jenkinsfile，配置了完整的CI/CD流程：

1. 代码检出
2. 依赖安装
3. 代码编译
4. APK构建
5. 构建产物归档

## 构建产物

构建完成后，APK文件位于：
- Debug版本：`app/build/outputs/apk/debug/app-debug.apk`
- Release版本：`app/build/outputs/apk/release/app-release.apk` 
=======
# apktest
