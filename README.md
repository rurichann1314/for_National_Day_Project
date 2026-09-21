# KMP National Day Project

这是 APP 部2026年国庆部门题 KMP 分支仓库，仓库内提供了一个基本的 JVM 模板，可以直接在本仓库的基础上修改。

![KMP](https://img.shields.io/badge/Kotlin_Multiplatform-7F52FF?style=flat&logo=kotlin&logoColor=white)

## 最低知识储备

[从 Kotlin 到 Kotlin Multiplatform](https://git.itouc.cn/ITStudio_OUC/From_Kotlin_to_Kotlin-Multiplatform)

完全掌握：引入 Kotlin，第一个 KMP 应用

简单了解：深入 Kotlin

## 小剧场

有一个小伙，叫 Carflo，他很喜欢跑团。

有一天他突然意识到一个问题：为什么市面上没有一个好看的，本地的，想怎么掷骰子都行的应用呢？要么是用网站，要么界面粗糙难看。

“哎呀，这样下去是没有好结果的，我必须立刻出手。”

## 评判标准

### 必要 共140分

- [x] 50分 至少可以 roll D6 任意次

- [ ] 30分 可以实现 roll D4，D6，D8，D12（或者更多）任意切换

- [ ] 20分 可以实现 roll n D6（指同时掷出 n 个六面骰并将点数加和）

- [ ] 20分 可以实现 roll n D m（指同时掷出 n 个 m 面骰并将点数加和）

- [ ] 20分 可以实现 roll $n_1$ D $m_1$ , $n_2$ D $m_2$ ...... 任意组合，最后点数取和

### 额外 共35分

- [ ] 5分 支持 Android 平台的编译与安装

- [ ] 5分 支持浏览器 WASM 编译

- [ ] 5分 在 GitHub Pages 上部署 WASM 编译包

- [ ] 5分 在 GitHub Action 上完成三大 JVM 平台安装包编译并 release

- [ ] 5分 有应用图标

- [ ] 5分 好看，达到我的审美

- [ ] 5分 根据实现方法，代码格式化等的特殊得分

## 提交方法

如果你是新开了一个代码仓库，请先在你的仓库里写一份 README，把这里的`评判标准`复制过去，然后把你完成的条目前面打上一个 x。

把你的仓库上传到任意公开的 git 托管平台。

在此仓库中开一个 issue，并写明你的仓库地址，我会在 issue 中给你点评。