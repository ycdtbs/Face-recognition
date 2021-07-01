import Home from "@/views/Home.vue";

export default [

  {
    path: "*",
    name: "WebRTC人脸识别",
    component: () => import("@/views/WebRTCFaceRecognition.vue"),
  }
];
