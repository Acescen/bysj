<template>
  <div id="app" style="margin: 0;padding: 0;width: 100%;height: 100%;position: absolute;background-color: #dffaff">
    <Nav v-if="navIsShow" />
    <router-view/>
  </div>
</template>

<script>
    import Nav from './components/Nav'

    export default {
        name: 'app',
        components: {
            Nav
        },
        computed: {
            navIsShow() {
                return this.$store.state.isLogin
            }
        }
    }
</script>

<style>
  body {
    margin: 0;
    padding: 0;
  }
</style>
