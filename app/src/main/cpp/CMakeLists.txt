# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

cmake_minimum_required(VERSION 3.22.1)
# 包含 Conan 生成的工具链（非常重要）
include(${CMAKE_BINARY_DIR}/conan_toolchain.cmake)

project("modellbox")

set(CMAKE_VERBOSE_MAKEFILE ON)
message("项目名称: ${CMAKE_PROJECT_NAME}")
message("CMake版本: ${CMAKE_VERSION}")

# 查找依赖
find_package(ZLIB 1.3.1 REQUIRED)
find_package(OpenSSL 3.5.2 REQUIRED)
#find_package(spdlog REQUIRED)
find_package(CURL REQUIRED)
find_package(beauty REQUIRED)
find_package(Boost REQUIRED)
find_package(fmt REQUIRED)
#find_package(Poco REQUIRED)
find_package(libmodbus REQUIRED)
find_package(Libevent REQUIRED)
find_package(amqpcpp REQUIRED)

# llama.cpp
# 启用 Vulkan 支持 —— 关键！
add_compile_definitions(GGML_VULKAN=1)
add_subdirectory(${CMAKE_SOURCE_DIR}/../../../../llama.cpp llama_build)

add_library(${CMAKE_PROJECT_NAME} SHARED
        native-lib.cpp)

target_link_libraries(${CMAKE_PROJECT_NAME}
        llama
        ggml
        vulkan
        android
        log
        pthread
        ZLIB::ZLIB
        openssl::openssl
#        spdlog::spdlog
        CURL::libcurl
        beauty::beauty
        boost::boost
        fmt::fmt
#        Poco::Poco
        libmodbus::libmodbus
        libevent::libevent
        amqpcpp)

# 设置 C++ 标准
target_compile_features(${CMAKE_PROJECT_NAME} PRIVATE cxx_std_17)