<template>
  <div id="app" :style="size">
    <router-view/>
  </div>
</template>

<script>
    export default {
        name: 'App',
        data(){
            return{
                size:{
                    width:'',
                    height:'',
                }
            }
        },
        methods:{
            getHeight(){
                return this.size.height = window.innerHeight - 20 + 'px';
            },
            getwidth(){
                return this.size.width = window.width - 20 + 'px';
            }
        },

        created() {
            this.getHeight();
            this.getwidth();
        }
    }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
